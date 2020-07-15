package vuong.hx.tayduky.ui.view_interfaces;

import java.util.List;

import vuong.hx.tayduky.models.Character;

public interface CharacterListView extends ToastView{
    void loadCharactersList(List<Character> characters);
}
