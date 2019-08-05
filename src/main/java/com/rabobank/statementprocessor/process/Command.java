package com.rabobank.statementprocessor.process;

import lombok.Getter;

public enum Command {
  ADD('+') {
    @Override
    public double execute(double a, double b) {
      return a + b;
    }
  },

  SUBTRACT('-') {
    @Override
    public double execute(double a, double b) {
      return a - b;
    }
  };

  @Getter
  private char operation;

  public abstract double execute(double a, double b);

  Command(final char operation) {
    this.operation = operation;
  }
}
