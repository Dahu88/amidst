package amidst.gui.menu;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import amidst.mojangapi.world.CoordinatesInWorld;
import amidst.mojangapi.world.MovablePlayerList;
import amidst.mojangapi.world.Player;

public class MovePlayerPopupMenu {
	private final Actions actions;
	private final MovablePlayerList movablePlayerList;
	private final CoordinatesInWorld targetCoordinates;

	public MovePlayerPopupMenu(Actions actions,
			MovablePlayerList movablePlayerList,
			CoordinatesInWorld targetCoordinates) {
		this.actions = actions;
		this.movablePlayerList = movablePlayerList;
		this.targetCoordinates = targetCoordinates;
	}

	public void show(Component component, int x, int y) {
		createPlayerMenu().show(component, x, y);

	}

	private JPopupMenu createPlayerMenu() {
		JPopupMenu result = new JPopupMenu();
		for (Player player : movablePlayerList) {
			result.add(createPlayerMenuItem(player, targetCoordinates));
		}
		return result;
	}

	private JMenuItem createPlayerMenuItem(final Player player,
			final CoordinatesInWorld targetCoordinates) {
		JMenuItem result = new JMenuItem(player.getPlayerName());
		result.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				actions.movePlayer(player, targetCoordinates);
			}
		});
		return result;
	}
}
