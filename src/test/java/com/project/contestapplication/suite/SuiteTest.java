package com.project.contestapplication.suite;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.suite.api.IncludeClassNamePatterns;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("JUnit Platform Suite Demo")
@SelectPackages("com")
//@SelectPackages("com.project.contestapplication.contest")
//@IncludeClassNamePatterns(".*Test")
public class SuiteTest {

}
