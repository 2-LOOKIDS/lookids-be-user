package lookids.user.point.application;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lookids.user.common.entity.BaseResponseStatus;
import lookids.user.common.exception.BaseException;
import lookids.user.point.domain.Point;
import lookids.user.point.dto.in.PointRequestDto;
import lookids.user.point.dto.out.PointResponseDto;
import lookids.user.point.infrastructure.PointRepository;

@Service
@AllArgsConstructor
@Slf4j
public class PointServiceImpl implements PointService {

	private final PointRepository pointRepository;

	@Override
	public void createPoint(PointRequestDto pointRequestDto) {
		pointRepository.save(pointRequestDto.toEntity());
	}

	@Override
	public void deletePoint(Long id) {
		Point point = pointRepository.findById(id)
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_DATA));
		pointRepository.deleteById(point.getId());
	}

	@Override
	public List<PointResponseDto> readPointType(String userUuid, Boolean type) {
		List<Point> pointList = pointRepository.findByUserUuidAndType(userUuid, type);
		return pointList.stream().map(PointResponseDto::toDto).toList();
	}

	@Override
	public List<PointResponseDto> readPointList(String userUuid) {
		List<Point> pointList = pointRepository.findByUserUuid(userUuid);
		return pointList.stream().map(PointResponseDto::toDto).toList();
	}
}
