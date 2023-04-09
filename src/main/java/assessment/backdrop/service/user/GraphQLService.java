package assessment.backdrop.service.user;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class GraphQLService {
    private final FetchVerifiedUser fetchVerifiedUser;
    @Value("classpath:schema.graphql")
    private Resource resource;

    private GraphQL graphQL;

    @PostConstruct
    private void getSchema() throws IOException {
        File schema = resource.getFile();
        TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser().parse(schema);
        RuntimeWiring runtimeWiring = RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("findUserAccountNameByAccountNumberAndBankCode", fetchVerifiedUser))
                .build();

        GraphQLSchema graphQLSchema = new SchemaGenerator().makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);
        graphQL = GraphQL.newGraphQL(graphQLSchema).build();
    }

    public GraphQL getGraphQL () {
        return graphQL;
    }
}
