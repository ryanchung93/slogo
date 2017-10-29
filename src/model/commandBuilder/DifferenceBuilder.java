package model.commandBuilder;

import model.Command;
import model.SLogoException;
import model.StackGroupCommandBuilder;
import model.TokenDispenser;
import model.commands.NumberCommand;
import model.commands.Product;
import model.commands.Sum;

public class DifferenceBuilder extends StackGroupCommandBuilder {

	@Override
	public Command build(TokenDispenser dispenser, Command last) throws SLogoException {
		return new Sum(new Product(new NumberCommand(-1), dispenser.getNextCommand()), last);
	}
}
