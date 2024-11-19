package lookids.user.common.entity;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BaseResponseStatus {

	/**
	 * 200: 요청 성공
	 **/
	SUCCESS(HttpStatus.OK, true, 200, "요청에 성공하였습니다."),

	/**
	 * 400 : security 에러
	 */
	WRONG_JWT_TOKEN(HttpStatus.UNAUTHORIZED, false, 401, "다시 로그인 해주세요"), NO_SIGN_IN(HttpStatus.UNAUTHORIZED, false, 402,
		"로그인을 먼저 진행해주세요"), NO_ACCESS_AUTHORITY(HttpStatus.FORBIDDEN, false, 403, "접근 권한이 없습니다"), DISABLED_USER(
		HttpStatus.FORBIDDEN, false, 404, "비활성화된 계정입니다. 계정을 복구하시겠습니까?"), FAILED_TO_RESTORE(
		HttpStatus.INTERNAL_SERVER_ERROR, false, 405, "계정 복구에 실패했습니다. 관리자에게 문의해주세요."), NO_EXIST_OAUTH(
		HttpStatus.NOT_FOUND, false, 406, "소셜 로그인 정보가 존재하지 않습니다."),

	/**
	 * 900: 기타 에러
	 */
	INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, false, 900, "Internal server error"), SSE_SEND_FAIL(
		HttpStatus.INTERNAL_SERVER_ERROR, false, 901, "알림 전송에 실패하였습니다."),

	/**
	 * 2000: users service error
	 */
	// token
	TOKEN_NOT_VALID(HttpStatus.UNAUTHORIZED, false, 2001, "토큰이 유효하지 않습니다."),

	// Users
	NO_EXIST_USER(HttpStatus.NOT_FOUND, false, 2105, "존재하지 않는 멤버 정보입니다."), DUPLICATED_NICKNAME(HttpStatus.CONFLICT,
		false, 2010, "이미 사용중인 닉네임입니다."), DUPLICATED_TAG(HttpStatus.CONFLICT, false, 2011, "태그 생성 실패"), SAME_NICKNAME(
		HttpStatus.CONFLICT, false, 2011, "현재 사용중인 닉네임입니다."), INVALID_EMAIL_ADDRESS(HttpStatus.BAD_REQUEST, false, 2012,
		"이메일을 다시 확인해주세요."),

	/**
	 * 6000: gpt-api error
	 */
	// Media
	NO_EXIST_DATA(HttpStatus.NOT_FOUND, false, 404, "데이터를 찾을 수 없습니다");

	private final HttpStatus httpStatusCode;
	private final boolean isSuccess;
	private final int code;
	private final String message;
}