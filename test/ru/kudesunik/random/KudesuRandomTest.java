package ru.kudesunik.random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * This class used only for testing some utility methods of pseudorandom number generator;
 * <br>Random uniformity for <code>nextInt()</code> and <code>nextLong()</code> methods was checked by much more complex statistical test suites
 * 
 * @since 2020
 * @version 1.0.0
 * @author Kudesunik
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class KudesuRandomTest {
	
	private final Random defaultRandom;
	
	public KudesuRandomTest() {
		this.defaultRandom = new Random();
	}
	
	@Test
	public void test01SetSeed() {
		long seed = defaultRandom.nextLong();
		KudesuRandom random1 = new KudesuRandom(seed);
		KudesuRandom random2 = new KudesuRandom(seed);
		for(int i = 0; i < 100000; i++) {
			assertEquals(random1.nextInt(), random2.nextInt());
			assertEquals(random1.nextLong(), random2.nextLong());
		}
	}
	
	@Test
	public void test02SetCounter() {
		long seed = defaultRandom.nextLong();
		KudesuRandom random1 = new KudesuRandom(seed);
		KudesuRandom random2 = new KudesuRandom(seed);
		for(int i = 100000; i < 200000; i += 2) {
			assertEquals(random1.getInt(i), random1.getInt(i));
			assertEquals(random1.getInt(i), random2.getInt(i));
			assertEquals(random1.getLong(i), random1.getLong(i));
			assertEquals(random1.getLong(i), random2.getLong(i));
		}
	}
	
	@Test
	public void test03NextIntRangeMax() {
		KudesuRandom random = new KudesuRandom();
		for(int i = 0; i < 10000000; i++) {
			int result = random.nextIntRange(5);
			assertTrue(result >= 0);
			assertTrue(result < 5);
		}
	}
	
	@Test
	public void test04NextIntRangeMinMax() {
		KudesuRandom random = new KudesuRandom();
		for(int i = 0; i < 10000000; i++) {
			int result = random.nextIntRange(5, 10);
			assertTrue(result >= 5);
			assertTrue(result < 10);
		}
	}
	
	@Test
	public void test05NextLongRangeMax() {
		KudesuRandom random = new KudesuRandom();
		for(int i = 0; i < 10000000; i++) {
			long result = random.nextLongRange(5);
			assertTrue(result >= 0);
			assertTrue(result < 5);
		}
	}
	
	@Test
	public void test06NextLongRangeMinMax() {
		KudesuRandom random = new KudesuRandom();
		for(int i = 0; i < 10000000; i++) {
			long result = random.nextLongRange(5, 10);
			assertTrue(result >= 5);
			assertTrue(result < 10);
		}
	}
	
	@Test
	public void test07Instantiation() {
		assertNotEquals(new KudesuRandom(), new KudesuRandom());
	}
	
	@Test
	public void test08Equality() {
		long seed = defaultRandom.nextLong();
		assertEquals(new KudesuRandom(seed), new KudesuRandom(seed));
	}
	
	@Test
	public void test09Reset() {
		KudesuRandom random = new KudesuRandom();
		int result = random.nextInt();
		for(int i = 0; i < 10000000; i++) {
			random.nextInt();
		}
		random.reset();
		assertEquals(result, random.nextInt());
	}
}
