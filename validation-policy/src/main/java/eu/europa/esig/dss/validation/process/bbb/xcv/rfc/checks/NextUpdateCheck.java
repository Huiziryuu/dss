package eu.europa.esig.dss.validation.process.bbb.xcv.rfc.checks;

import java.util.Date;

import eu.europa.esig.dss.jaxb.detailedreport.XmlRFC;
import eu.europa.esig.dss.validation.MessageTag;
import eu.europa.esig.dss.validation.policy.rules.Indication;
import eu.europa.esig.dss.validation.policy.rules.SubIndication;
import eu.europa.esig.dss.validation.process.ChainItem;
import eu.europa.esig.dss.validation.process.bbb.XmlInfoBuilder;
import eu.europa.esig.dss.validation.reports.wrapper.RevocationWrapper;
import eu.europa.esig.jaxb.policy.TimeConstraint;

public class NextUpdateCheck extends ChainItem<XmlRFC> {

	private final RevocationWrapper revocationData;

	public NextUpdateCheck(XmlRFC result, RevocationWrapper revocationData, TimeConstraint constraint) {
		super(result, constraint);

		this.revocationData = revocationData;
	}

	@Override
	protected boolean process() {
		if (revocationData != null) {
			Date nextUpdate = revocationData.getNextUpdate();
			if (nextUpdate == null) {
				return false;
			}
			addInfo(XmlInfoBuilder.createNextUpadteInfo(nextUpdate));
			return true;
		}
		return false;
	}

	@Override
	protected MessageTag getMessageTag() {
		return MessageTag.BBB_RFC_NUP;
	}

	@Override
	protected MessageTag getErrorMessageTag() {
		return MessageTag.BBB_RFC_NUP_ANS;
	}

	@Override
	protected Indication getFailedIndicationForConclusion() {
		return Indication.FAILED;
	}

	@Override
	protected SubIndication getFailedSubIndicationForConclusion() {
		return null;
	}

}