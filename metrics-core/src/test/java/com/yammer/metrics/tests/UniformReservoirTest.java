package com.yammer.metrics.tests;

import com.yammer.metrics.Snapshot;
import com.yammer.metrics.UniformReservoir;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class UniformReservoirTest {
    @Test
    @SuppressWarnings("unchecked")
    public void aReservoirOf100OutOf1000Elements() throws Exception {
        final UniformReservoir reservoir = new UniformReservoir(100);
        for (int i = 0; i < 1000; i++) {
            reservoir.update(i);
        }

        final Snapshot snapshot = reservoir.getSnapshot();

        assertThat(reservoir.size())
                .isEqualTo(100);

        assertThat(snapshot.size())
                .isEqualTo(100);

        for (double i : snapshot.getValues()) {
            assertThat(i)
                    .isLessThan(1000)
                    .isGreaterThanOrEqualTo(0);
        }
    }

}
