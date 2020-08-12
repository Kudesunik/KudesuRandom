package ru.kudesunik.random;

/**
 * Counter-based pseudorandom number generation algorithm;
 * <br>Based on middle-square method with Weyl sequence, but with major changes;
 * <br>It is very fast counter-based random generator which can pass statistical tests faultless with more than 16 TB generated data;
 * <p>For this generator, seeding is important process: seed should be about half ones and half zeros with irregular template, so this class contains a generator of such seeds;
 * <p>Algorithm uniformity tested for nextInt() and nextLong() methods by:
 * <br>NIST STS
 * <br>GJrand STS
 * <br>PractRand STS on 16 TB (2^44 bytes) data with no fails;
 * 
 * <p>Warning: this alghorithm implementation not thread safe!
 * 
 * @since 2020
 * @version 1.0.0
 * @author Kudesunik
 */
public class KudesuRandom implements Cloneable {
	
	private static final int SEED_BIT_CHECK = 2;
	private static final int SEED_BIT_SHIFT = 15;
	
	private long seed = -4995613063753901367L; //Initial seed that used for seeding
	private long count;
	
	public KudesuRandom() {
		setSeed(System.nanoTime() ^ System.identityHashCode(this));
		count = 1;
	}
	
	public KudesuRandom(long seed) {
		setSeed(seed);
	}
	
	public void setSeed(long seed) {
		count = seed;
		long preSeed = nextLong();
		int bit = Long.bitCount(preSeed) - 32;
		int bitCount;
		if((bitCount = Math.abs(bit)) <= SEED_BIT_CHECK) {
			this.seed = preSeed;
			return;
		}
		int mark = (bit > 0) ? 0 : 1;
		for(int i = 0; (i < 63) && (bitCount > SEED_BIT_CHECK); i++) {
			preSeed = Long.rotateRight(preSeed, SEED_BIT_SHIFT);
			if((preSeed & 1) != mark) {
				preSeed ^= 1;
				bitCount--;
			}
		}
		this.seed = preSeed;
		count = 1;
	}
	
	public void setCounter(long count) {
		this.count = (count == 0) ? 1 : count;
	}
	
	public int nextInt() {
		long x = seed * count;
		long y = x;
		x = ((x >>> 32) * x) + count;
		return (int) ((((x >>> 32) * x) + y + (count++)) >>> 32);
	}
	
	public int nextIntRange(int max) {
		if(max <= 0) {
			throw new IllegalArgumentException("illegal bound: " + max + " (must be positive)");
		}
		int t = nextInt();
		int m = max - 1;
		if((max & m) == 0) {
			return t & m;
		}
		int u = t >>> 1;
		while((u + m - (t = u % max)) < 0) {
			u = nextInt() >>> 1;
		}
		return t;
	}
	
	public int nextIntRange(int min, int max) {
		return min + nextIntRange(Math.max(min, max) - min);
	}
	
	public boolean nextBoolean() {
		return nextInt() < 0;
	}
	
	public float nextFloat() {
		return (nextInt() >>> 9) * 0x1.0P-23f;
	}
	
	public long nextLong() {
		long x = seed * count;
		long y = x;
		x = ((x >>> 32) * x) + count;
		x = ((x >>> 32) * x) + y;
		return ((((x >>> 32) * x) + y + count) >>> 32) ^ (x + (count++));
	}
	
	public long nextLongRange(long max) {
		if(max <= 0) {
			throw new IllegalArgumentException("illegal bound: " + max + " (must be positive)");
		}
		long t = nextLong();
		long m = max - 1L;
		if((max & m) == 0) {
			return t & m;
		}
		long u = t >>> 1;
		while((u + m - (t = u % max)) < 0) {
			u = nextLong() >>> 1;
		}
		return t;
	}
	
	public double nextDouble() {
		return (nextLong() >>> 12) * 0x1.0P-52d;
	}
	
	public void nextBytes(byte[] bytes) {
		int i = bytes.length;
		int n = 0;
		while(i != 0) {
			n = Math.min(i, 8);
			for(long bits = nextLong(); (n--) != 0; bits >>>= 8) {
				bytes[--i] = (byte) bits;
			}
		}
	}
	
	@Override
	public KudesuRandom clone() {
		KudesuRandom random = new KudesuRandom(seed);
		random.count = this.count;
		return random;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (count ^ (count >>> 32));
		result = prime * result + (int) (seed ^ (seed >>> 32));
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		if(obj == null) {
			return false;
		}
		if(getClass() != obj.getClass()) {
			return false;
		}
		KudesuRandom other = (KudesuRandom) obj;
		if(count != other.count) {
			return false;
		}
		return (seed == other.seed);
	}
}
