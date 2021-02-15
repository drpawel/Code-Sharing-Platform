package platform;

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
        code.setTimeRestriction(code.getTime()>0);
        code.setViewsRestriction(code.getViews()>0);
        codeRepository.save(code);
    }

    public Optional<Code> getCode(String s){
        String s2 = s.replace("-", "");
        UUID uuid = new UUID(
                new BigInteger(s2.substring(0, 16), 16).longValue(),
                new BigInteger(s2.substring(16), 16).longValue());
        return codeRepository.findById(uuid);
    }

    public List<Code> getLatest() {
        return codeRepository.findTop10ByTimeRestrictionFalseAndViewsRestrictionFalseOrderByDateDesc();
    }

    public void checkRestrictions(Code code){
        if (code.isTimeRestriction()) {
            long consumedTime = consumedTime(code.getDate());
            if (consumedTime < code.getTime()) {
                code.setTime(code.getTime() - consumedTime);
                codeRepository.save(code);
            } else {
                codeRepository.delete(code);
                throw new CodeNotFoundException();
            }
        }

        if (code.isViewsRestriction()) {
            if(code.getViews() == 1){
                code.setViews(code.getViews() - 1);
                codeRepository.delete(code);
            }else if (code.getViews() > 0) {
                code.setViews(code.getViews() - 1);
                codeRepository.save(code);
            } else {
                codeRepository.delete(code);
                throw new CodeNotFoundException();
            }
        }
    }

    public long consumedTime(LocalDateTime date) {
        LocalTime dateOfCode = date.toLocalTime();
        LocalTime thisMoment = LocalTime.now();
        return thisMoment.toSecondOfDay() - dateOfCode.toSecondOfDay();
    }
}
