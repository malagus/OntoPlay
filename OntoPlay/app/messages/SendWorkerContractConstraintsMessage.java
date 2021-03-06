package messages;

import conf.Config;
import jade.core.AID;
import jade.lang.acl.ACLMessage;
import agent.ACLBaseMessage;

public class SendWorkerContractConstraintsMessage extends ACLBaseMessage {
	private String contractDescriptionOWL;

	public SendWorkerContractConstraintsMessage(String contractDescriptionOWL, String recipientAID) {
		super(recipientAID);
		this.contractDescriptionOWL = contractDescriptionOWL;
	}

	@Override
	protected ACLMessage createMessage() {
		ACLMessage message = new ACLMessage(ACLMessage.REQUEST);
		//TODO: Use Pawel's library in some way to send the OWL description of the resource looking for teams to join.
		message.setContent(contractDescriptionOWL);
		message.setOntology(Config.GUIOntology);
		
		return message;
	}

}
