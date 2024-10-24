package lookids.user.agreement.application;

import java.beans.Transient;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lookids.user.agreement.domain.Agreement;
import lookids.user.agreement.dto.in.AgreementRequestDto;
import lookids.user.agreement.dto.in.AgreementUpdateDto;
import lookids.user.agreement.dto.out.AgreementResponseDto;
import lookids.user.agreement.infrastructure.AgreementRepository;
import lookids.user.common.entity.BaseResponseStatus;
import lookids.user.common.exception.BaseException;

@Service
@AllArgsConstructor
@Slf4j
public class AgreementServiceImpl implements AgreementService {

	private final AgreementRepository agreementRepository;

	@Override
	public void createAgreement(AgreementRequestDto agreementRequestDto) {
		agreementRepository.save(agreementRequestDto.toEntity());
	}

	@Override
	public void updateAgreement(AgreementUpdateDto agreementUpdateDto) {
		Agreement agreement = agreementRepository.findByUserUuidAndPolicyCode(agreementUpdateDto.getUserUuid(),
			agreementUpdateDto.getPolicyCode()).orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_DATA));
		agreement.updateAgree(agreementUpdateDto.getAgree());
		agreementRepository.save(agreement);
	}

	@Override
	public void deleteAgreement(String userUuid, String policyCode) {
		Agreement agreement = agreementRepository.findByUserUuidAndPolicyCode(userUuid, policyCode)
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_DATA));
		agreementRepository.deleteById(agreement.getId());
	}

	@Transient
	@Override
	public void updatePolicy(AgreementRequestDto agreementRequestDto) {
		Agreement agreement = agreementRepository.findByUserUuidAndTypeAndActivated(agreementRequestDto.getUserUuid(),
			agreementRequestDto.getType(), true).orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_DATA));
		agreement.updateActivated(false);
		agreementRepository.save(agreement);
		agreementRepository.save(agreementRequestDto.toEntity());
	}

	@Override
	public AgreementResponseDto readAgreement(String userUuid, String policyCode) {
		return AgreementResponseDto.toDto(agreementRepository.findByUserUuidAndPolicyCode(userUuid, policyCode)
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_DATA)));
	}

	@Override
	public List<AgreementResponseDto> readAgreementList(String userUuid) {
		List<Agreement> agreementList = agreementRepository.findByUserUuidAndActivated(userUuid, true);
		return agreementList.stream().map(AgreementResponseDto::toDto).toList();
	}
}
