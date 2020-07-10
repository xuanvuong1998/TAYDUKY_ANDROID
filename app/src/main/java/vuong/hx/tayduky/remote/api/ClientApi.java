package vuong.hx.tayduky.remote.api;

import vuong.hx.tayduky.remote.services.ActorService;
import vuong.hx.tayduky.remote.services.AdminService;
import vuong.hx.tayduky.remote.services.AuthService;
import vuong.hx.tayduky.remote.services.ChallengeService;
import vuong.hx.tayduky.remote.services.CharacterService;
import vuong.hx.tayduky.remote.services.SceneRoleService;
import vuong.hx.tayduky.remote.services.SceneToolService;
import vuong.hx.tayduky.remote.services.ToolService;

public class ClientApi extends BaseApi{

    public AuthService getAuthService(){
        return this.getService(AuthService.class, ApiConfig.BASE_URL);
    }

    public ActorService getActorService(){
        return this.getService(ActorService.class, ApiConfig.BASE_URL);
    }
    public AdminService getAdminService(){
        return this.getService(AdminService.class, ApiConfig.BASE_URL);
    }

    public ChallengeService getChallengeService(){
        return this.getService(ChallengeService.class, ApiConfig.BASE_URL);
    }

    public SceneToolService getSceneToolService(){
        return this.getService(SceneToolService.class, ApiConfig.BASE_URL);
    }

    public SceneRoleService getSceneRoleService(){
        return this.getService(SceneRoleService.class, ApiConfig.BASE_URL);
    }

    public CharacterService getCharacterService(){
        return this.getService(CharacterService.class, ApiConfig.BASE_URL);
    }

    public ToolService getToolService(){
        return this.getService(ToolService.class, ApiConfig.BASE_URL);
    }

}
