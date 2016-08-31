package org.econtact.data.entity.util;

import org.econtact.data.DataModelHelper;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

import static org.junit.Assert.assertEquals;

public class DataModelHelperTest {

    private Callable<Long> generateUidTask;

    @Before
    public void setUp() {
        generateUidTask = DataModelHelper::getUid;
    }

    @Test
    public void test1ThreadGenerate() throws ExecutionException, InterruptedException {
        testGenerateUid(1);
    }

    @Test
    public void test2ThreadGenerate() throws ExecutionException, InterruptedException {
        testGenerateUid(2);
    }

    @Test
    public void test3ThreadGenerate() throws ExecutionException, InterruptedException {
        testGenerateUid(3);
    }

    @Test
    public void test4ThreadGenerate() throws ExecutionException, InterruptedException {
        testGenerateUid(4);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWrongThreadGenerate() throws ExecutionException, InterruptedException {
        testGenerateUid(-1);
    }

    private void testGenerateUid(final int threadCount) throws InterruptedException, ExecutionException {
        List<Long> generatedUids = generateUid(threadCount);
        List<Long> expectedList = buildExpectedListWithValues(
                fingMinValue(generatedUids), threadCount);

        assertEquals(threadCount, generatedUids.size());
        assertEquals(expectedList, generatedUids);
    }

    private List<Long> generateUid(final int threadCount) throws InterruptedException, ExecutionException {
        final List<Callable<Long>> tasks = Collections.nCopies(threadCount, generateUidTask);
        final ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        final List<Future<Long>> futures = executorService.invokeAll(tasks);

        final List<Long> generatedUids = new ArrayList<>(futures.size());
        for (Future<Long> future : futures) {
            generatedUids.add(future.get());
        }
        Collections.sort(generatedUids);
        return generatedUids;
    }

    private Long fingMinValue(Collection<Long> values) {
        return values.stream().min(Long::compare).orElse(0L);
    }

    private List<Long> buildExpectedListWithValues(Long minValue, int length) {
        final List<Long> result = new ArrayList<>(length);
        for (int i = 0; i < length; i++) {
            result.add(minValue++);
        }
        return result;
    }
}
