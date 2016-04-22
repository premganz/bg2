package org.spo.svc2.trx.def;

import org.spo.ifs2.template.web.Constants;
import org.springframework.stereotype.Component;
@Component
public class ConstantsImpl implements Constants {

	@Override
	public String getRepoPath() {
		return "C:\\Users\\premganesh\\git\\bg1_3\\src\\main\\resources\\data-cms";
	}

	@Override
	public String getLandingPage() {
		return "trx/M01/LA01T";
	}

}
