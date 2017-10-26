package model.commandBuilder;

import model.Command;
import model.CommandBuilder;
import model.SLogoException;
import model.TokenDispenser;
import model.commands.NumberCommand;
import model.commands.Pow;
import model.commands.Product;

public class QuotientBuilder implements CommandBuilder {

	@Override
	public Command build(TokenDispenser dispenser) throws SLogoException {
		return new Product(dispenser.getNextCommand(), new Pow(dispenser.getNextCommand(), new NumberCommand(-1)));
	}

}
