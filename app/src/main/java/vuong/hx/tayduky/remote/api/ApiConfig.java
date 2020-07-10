package vuong.hx.tayduky.remote.api;

public class ApiConfig {
    public static final String BASE_URL = "http://10.0.2.2:2310/api/";
    public static final String HOST_URL = "http://10.0.2.2:2310/";

    public interface Apis{
         interface Auth{
             String LOGIN = "auth";
             String BEARER_PREFIX = "Bearer ";
         }
         interface Actor {
             String BASE_NAME = "actors";
             String GET_ALL = BASE_NAME;
             String GET_BY_ID = BASE_NAME + "/{actor_id}";
             String CREATE = BASE_NAME;
             String DELETE = BASE_NAME + "/{actor_id}";
             String UPDATE = BASE_NAME;
             String GET_INCOMING_ROLES = BASE_NAME + "/{actor_id}/incoming-roles";
             String GET_PLAYED_ROLES = BASE_NAME + "/{actor_id}/played-roles";
        }
        interface Admin{
             String BASE_NAME = "admins";
             String CREATE = BASE_NAME;
             String DELETE = BASE_NAME + "/{id}";
        }
        interface Challenge{
            String BASE_NAME = "challenges";
            String GET_ALL = BASE_NAME;
            String GET_BY_ID = BASE_NAME + "/{challenge_id}";
            String CREATE = BASE_NAME;
            String DELETE = BASE_NAME + "/{challenge_id}";
            String UPDATE = BASE_NAME ;
        }

        interface Character{
            String BASE_NAME = "characters";
            String GET_ALL = BASE_NAME;
            String GET_BY_ID = BASE_NAME + "/{character_id}";
            String CREATE = BASE_NAME;
            String DELETE = BASE_NAME + "/{character_id}";
            String UPDATE = BASE_NAME ;
        }

        interface Tool{
            String BASE_NAME = "tools";
            String GET_ALL = BASE_NAME;
            String GET_BY_ID = BASE_NAME + "/{tool_id}";
            String CREATE = BASE_NAME;
            String DELETE = BASE_NAME + "/{tool_id}";
            String UPDATE = BASE_NAME ;
        }

        interface SceneRole{
            String BASE_NAME = "sceneroles";
            String GET_ALL = BASE_NAME;
            String GET_BY_ID = BASE_NAME + "/{sceneRole_id}";
            String CREATE = BASE_NAME;
            String DELETE = BASE_NAME + "/{sceneRole_id}";
            String UPDATE = BASE_NAME ;
        }

        interface SceneTool{
            String BASE_NAME = "scenetools";
            String GET_ALL = BASE_NAME;
            String GET_BY_ID = BASE_NAME + "/{challenge_id}/{tool_id}";
            String CREATE = BASE_NAME;
            String DELETE = BASE_NAME + "/{challenge_id}/{tool_id}";
            String UPDATE = BASE_NAME ;
        }

        
    }
}
