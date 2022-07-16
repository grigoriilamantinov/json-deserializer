package com.manatee.mymap.requests;

import java.util.List;

public interface Request <E> {
    List<E> getRequest(String string);
}
