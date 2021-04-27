package com.vmware.bhenao.interfaces;

import com.vmware.bhenao.exceptions.StopPlayingException;

import java.io.IOException;

public interface WeeklyProblem {

    void Start() throws StopPlayingException, IOException;
}
