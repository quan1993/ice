package disruptor;

import com.lmax.disruptor.EventHandler;

public class LongEventHandler implements EventHandler<LongEvent>
{
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch)
    {
        System.out.println("Event: " + event);
        System.out.println("Event.value: " + event.getValue());
        System.out.println("sequence: " + sequence);
        System.out.println("endOfBatch: " + endOfBatch);
        
    }
}
