package com.heavenhr.recruitment.service;

/**
 * The Interface IRecruitmentService.
 */
public interface IRecruitmentService<I, O> {

  /**
   * <p>process.</p>
   *
   * @param input a I object.
   * @return a O object.
   */
  O process(I input) throws Exception;

}
