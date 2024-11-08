package lookids.user.agreement.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lookids.user.agreement.domain.Agreement;
import lookids.user.agreement.vo.in.AgreementRequestVo;

@Getter
@NoArgsConstructor
public class AgreementRequestDto {

	private String userUuid;
	private String policyCode;
	private Boolean agree;
	private String type;

	@Builder
	public AgreementRequestDto(String userUuid, String policyCode, Boolean agree, String type) {
		this.userUuid = userUuid;
		this.policyCode = policyCode;
		this.agree = agree;
		this.type = type;
	}

	public static AgreementRequestDto toDto(AgreementRequestVo agreementRequestVo) {
		return AgreementRequestDto.builder()
			.userUuid(agreementRequestVo.getUserUuid())
			.policyCode(agreementRequestVo.getPolicyCode())
			.agree(agreementRequestVo.getAgree())
			.type(agreementRequestVo.getType())
			.build();
	}

	public Agreement toEntity() {
		return Agreement.builder().userUuid(userUuid).policyCode(policyCode).agree(agree).type(type).build();
	}

	public Agreement toUpdate(Agreement agreement, Boolean agree) {
		return Agreement.builder()
			.id(agreement.getId())
			.userUuid(agreement.getUserUuid())
			.policyCode(agreement.getPolicyCode())
			.agree(agree)
			.type(agreement.getType())
			.build();
	}
}
