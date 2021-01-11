package platform;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CodeService {
    private final CodeRepository codeRepository;

    public CodeService(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }

    public void save(Code code){
        codeRepository.save(code);
    }

    public void delete(Code code){
        codeRepository.delete(code);
    }

    public Optional<Code> getCode(String s){
        String s2 = s.replace("-", "");
        UUID uuid = new UUID(
                new BigInteger(s2.substring(0, 16), 16).longValue(),
                new BigInteger(s2.substring(16), 16).longValue());
        Optional<Code> code = codeRepository.findById(uuid);
        return code;
    }

    public List<Code> getLatest() {
        PageRequest pageable = PageRequest.of(0, 10);
        return codeRepository.findLatest(0, 0,
                pageable).getContent();
        //return codeRepository.findTop10ByTimeEqualsAndViewsEqualsOrderByDateDesc(0,0);
    }

    public long consumedTime(LocalDateTime date) {
        LocalTime dateOfCode = date.toLocalTime();
        LocalTime thisMoment = LocalTime.now();
        long seconds = thisMoment.toSecondOfDay() - dateOfCode.toSecondOfDay();
        return seconds;
    }
}
