package benchmark;

import org.junit.Test;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;


public class BenchmarkRunnerTest {

    @Test
    public void RunBenchmark() throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(RedisRepositoryReadFromCacheBenchmarkTest.class.getSimpleName())
                .include(RedisRepositoryReadFromMongoBenchmarkTest.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }
}
