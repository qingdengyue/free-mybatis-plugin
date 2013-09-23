package com.seventh7.mybatis.generate;

import java.util.Collection;

/**
 * @author yanglin
 */
public abstract class GenerateModel {

  public static final GenerateModel START_WITH_MODEL = new StartWithModel();

  public static final GenerateModel END_WITH_MODEL = new EndWithModel();

  public static final GenerateModel CONTAIN_MODEL = new ContainModel();

  public static final GenerateModel getInstance(int identifier) {
    switch (identifier) {
      case 1:
        return END_WITH_MODEL;
      case 2:
        return CONTAIN_MODEL;
      default:
        return START_WITH_MODEL;
    }
  }

  public boolean matchAny(String[] patterns, String target) {
    for (String pattern : patterns) {
      if (apply(pattern, target)) {
        return true;
      }
    }
    return false;
  };

  public boolean matchAny(Collection<String> patterns, String target) {
    return matchAny(patterns.toArray(new String[patterns.size()]), target);
  }

  protected abstract boolean apply(String pattern, String target);

  public abstract int getIdentifier();

  static class StartWithModel extends GenerateModel {

    @Override
    protected boolean apply(String pattern, String target) {
      return target.startsWith(pattern);
    }

    @Override
    public int getIdentifier() {
      return 0;
    }
  }

  static class EndWithModel extends GenerateModel {

    @Override
    protected boolean apply(String pattern, String target) {
      return target.endsWith(pattern);
    }

    @Override
    public int getIdentifier() {
      return 1;
    }
  }

  static class ContainModel extends GenerateModel {

    @Override
    protected boolean apply(String pattern, String target) {
      return target.contains(pattern);
    }

    @Override
    public int getIdentifier() {
      return 2;
    }
  }
}
