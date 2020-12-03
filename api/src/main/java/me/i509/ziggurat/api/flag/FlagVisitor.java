package me.i509.ziggurat.api.flag;

import java.util.UUID;

import org.jetbrains.annotations.Nullable;

public interface FlagVisitor {
	boolean visit(FlagType flag);

	void visitBoolean(FlagType.Bool flag, boolean value);

	void visitInt(FlagType.Int flag, int value);

	void visitDouble(FlagType.Double flag, double value);

	void visitUuid(FlagType.Uuid flag, UUID value);

	void visitString(FlagType.Str flag, String value);

	<V> void visitRegistryEntry(FlagType.RegistryEntry<V> flag, V value);

	<E extends Enum<E>> void visitEnum(FlagType.Enum<E> flag, Enum<E> value);

	<V> boolean visitSet(FlagType.Set<V> flag);

	<V> void visitSetValue(FlagType.Set<V> flag, V value);

	abstract class Abstract implements FlagVisitor {
		private final FlagVisitor parent;

		protected Abstract(@Nullable FlagVisitor parent) {
			this.parent = parent;
		}

		@Override
		public boolean visit(FlagType flag) {
			if (this.parent != null) {
				return this.parent.visit(flag);
			}

			return false;
		}

		@Override
		public void visitBoolean(FlagType.Bool flag, boolean value) {
			if (this.parent != null) {
				this.parent.visitBoolean(flag, value);
			}
		}

		@Override
		public void visitInt(FlagType.Int flag, int value) {
			if (this.parent != null) {
				this.parent.visitInt(flag, value);
			}
		}

		@Override
		public void visitDouble(FlagType.Double flag, double value) {
			if (this.parent != null) {
				this.parent.visitDouble(flag, value);
			}
		}

		@Override
		public void visitUuid(FlagType.Uuid flag, UUID value) {
			if (this.parent != null) {
				this.parent.visitUuid(flag, value);
			}
		}

		@Override
		public void visitString(FlagType.Str flag, String value) {
			if (this.parent != null) {
				this.parent.visitString(flag, value);
			}
		}

		@Override
		public <E extends Enum<E>> void visitEnum(FlagType.Enum<E> flag, Enum<E> value) {
			if (this.parent != null) {
				this.parent.visitEnum(flag, value);
			}
		}

		@Override
		public <V> void visitRegistryEntry(FlagType.RegistryEntry<V> flag, V value) {
			if (this.parent != null) {
				this.parent.visitRegistryEntry(flag, value);
			}
		}

		@Override
		public <V> boolean visitSet(FlagType.Set<V> flag) {
			if (this.parent != null) {
				return this.parent.visitSet(flag);
			}

			return false;
		}

		@Override
		public <V> void visitSetValue(FlagType.Set<V> flag, V value) {
			if (this.parent != null) {
				this.parent.visitSetValue(flag, value);
			}
		}
	}
}
