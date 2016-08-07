package game;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

class InputHandler extends AbstractAction {

	private Direction dir;
	private String state;

	InputHandler(Direction dir, String state) {
		this.dir = dir;
		this.state = state;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Changes the state of each key in the keyMap
		if (state.equalsIgnoreCase(Game.PRESSED)) {
			Game.keyMap.put(dir, true);
		} else if (state.equalsIgnoreCase(Game.RELEASED)) {
			Game.keyMap.put(dir, false);
		}
	}
}
