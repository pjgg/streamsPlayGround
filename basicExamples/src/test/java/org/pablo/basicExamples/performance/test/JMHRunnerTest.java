package org.pablo.basicExamples.performance.test;

import org.junit.Test;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;
import org.openjdk.jmh.runner.options.VerboseMode;

public class JMHRunnerTest {
	
	@Test
    public void runTests() throws RunnerException
    {		
		int warmupCount = 5;
        int runCount = 50;
        Options opts = new OptionsBuilder()
                .include("org.pablo.basicExamples.performance.test.*")
                .warmupTime(TimeValue.seconds(2))
                .warmupIterations(warmupCount)
                .measurementTime(TimeValue.seconds(2))
                .measurementIterations(runCount)
                .verbosity(VerboseMode.EXTRA)
                .forks(1)
                .build();

        new Runner(opts).run();
    }
}
