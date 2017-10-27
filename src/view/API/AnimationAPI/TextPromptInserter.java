package view.API.AnimationAPI;

import view.Animation.TextPromptView;

public interface TextPromptInserter {

	/**
	 * Give a textprompt to send commands to that textprompt.
	 * 
	 * @param tp
	 *            textprompt that takes in commands.
	 */
	public void addTextPrompt(TextPromptView tp);
	
}
