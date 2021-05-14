package com.uit.coursemanagement.service;

public interface IService<Input, Output> {
    Output execute(final Input input);
    Output execute();
}
