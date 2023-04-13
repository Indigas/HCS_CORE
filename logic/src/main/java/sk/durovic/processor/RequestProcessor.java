package sk.durovic.processor;

import sk.durovic.events.Event;
import sk.durovic.result.Result;

public interface RequestProcessor {

    Result<?> process(Event event);
}
