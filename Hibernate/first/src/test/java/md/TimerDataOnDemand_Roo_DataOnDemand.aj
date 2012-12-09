// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package md;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import md.Timer;
import md.TimerDataOnDemand;
import org.springframework.stereotype.Component;

privileged aspect TimerDataOnDemand_Roo_DataOnDemand {
    
    declare @type: TimerDataOnDemand: @Component;
    
    private Random TimerDataOnDemand.rnd = new SecureRandom();
    
    private List<Timer> TimerDataOnDemand.data;
    
    public Timer TimerDataOnDemand.getNewTransientTimer(int index) {
        Timer obj = new Timer();
        setCounter(obj, index);
        setMessage(obj, index);
        return obj;
    }
    
    public void TimerDataOnDemand.setCounter(Timer obj, int index) {
        Integer counter = new Integer(index);
        obj.setCounter(counter);
    }
    
    public void TimerDataOnDemand.setMessage(Timer obj, int index) {
        String message = "message_" + index;
        obj.setMessage(message);
    }
    
    public Timer TimerDataOnDemand.getSpecificTimer(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        Timer obj = data.get(index);
        Long id = obj.getId();
        return Timer.findTimer(id);
    }
    
    public Timer TimerDataOnDemand.getRandomTimer() {
        init();
        Timer obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return Timer.findTimer(id);
    }
    
    public boolean TimerDataOnDemand.modifyTimer(Timer obj) {
        return false;
    }
    
    public void TimerDataOnDemand.init() {
        int from = 0;
        int to = 10;
        data = Timer.findTimerEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'Timer' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<Timer>();
        for (int i = 0; i < 10; i++) {
            Timer obj = getNewTransientTimer(i);
            try {
                obj.persist();
            } catch (ConstraintViolationException e) {
                StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getConstraintDescriptor()).append(":").append(cv.getMessage()).append("=").append(cv.getInvalidValue()).append("]");
                }
                throw new RuntimeException(msg.toString(), e);
            }
            obj.flush();
            data.add(obj);
        }
    }
    
}
