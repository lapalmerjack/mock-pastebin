package platform.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.model.Code;
import platform.repositories.CodeRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CodeService {

    private final CodeRepository codeRepository;

    @Autowired
    public CodeService(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }

    public List<Code> getLatestCode() {
        System.out.println("GETTING LATEST CODE");
        List<Code> allCode = codeRepository.findAll();
        System.out.println(allCode);
        List<Code> latestCode = new ArrayList<>();
        int count = 0;
        for (int i = allCode.size()-1; i >=0; i--) {
            if (!isRestriction(allCode.get(i))) {
                if (count < 10) {
                    latestCode.add(allCode.get(i));
                    System.out.println(allCode.get(i));

                }
                count++;
            }
        }

        return latestCode;
    }

    public void addNewSnippet (Code code) {
        Optional<Code> codeOptional =
                codeRepository.findCode(code.getCode());
        if(codeOptional.isPresent()) {
            throw new IllegalStateException("code is already in database");
        }
        System.out.println(code + "has been added");
        codeRepository.save(code);
    }
    
    public Code getById(String id) {
        Optional<Code> findCode = codeRepository.findById(id);


        if(findCode.isPresent()) {
            if(!isRestriction(findCode.get()) || !findCode.get().getRestriction()) {
                return findCode.orElseGet(() -> null);
            }

            else {
                if(findCode.get().getTime() > 0) {
                    getRemainingTime(findCode.get());
                    System.out.println("UPDATED TIME " + findCode.get());

                    if(findCode.get().getTime() <= 0) {
                        findCode.get().setTimeUp(true);
                        System.out.println("deleting time");
                        codeRepository.delete(findCode.get());

                    }
                }

                if(findCode.get().getViews() > 0) {
                    if(findCode.get().getViews() == 1) {
                        findCode.get().setLastNumber(1);
                    }
                    minusViews(findCode.get());
                    System.out.println("going in");
                    codeRepository.save(findCode.get());


                    if(findCode.get().getViews() <= 0) {
                        codeRepository.delete(findCode.get());
                    }
                }


                }
            System.out.println(findCode.get());
            return findCode.get().getTime() < 0 || findCode.get().getViews() < 0 ? null : findCode.get();
        } else {
            return null;
        }

    }

    public void getRemainingTime(Code code) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime creationDate = LocalDateTime.parse(code.getDate(), formatter);

        if(code.getExpirationDate() == null) {
           code.setExpirationDate(creationDate.plusSeconds(code.getTime()));
           codeRepository.save(code);
        }

        code.setTime(ChronoUnit.SECONDS.between(LocalDateTime.now(), code.getExpirationDate()));
        if(code.getTime() < 0) {

            codeRepository.delete(code);

        }

    }

    private boolean isRestriction (Code code) {
        if(code.getViews() > 0 || code.getTime() > 0 || code.getRestriction()) {
           code.setRestriction(true);
           return true;

        } else {
            return false;
        }
    }

    public void minusViews(Code code) {
        code.setViews(code.getViews()-1);
    }





}
