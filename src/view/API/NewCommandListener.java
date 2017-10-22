package view.API;

/**
 * Listens for newly executed commands -- its methods must be called when
 * command is newly executed.
 * 
 * @author taekwhunchung
 *
 */
public interface NewCommandListener {

	public void addCommand(String command);
}
