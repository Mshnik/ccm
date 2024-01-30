package com.redpup.ccm;

import static com.google.common.truth.Truth.assertThat;

import com.redpup.ccm.proto.FooProtos.Foo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public final class FooProtoTest {

	@Test
	public void testProtoFramework() {
		Foo foo = Foo.newBuilder().setValue("Hello").build();
		assertThat(foo.getValue()).isEqualTo("Hello");
	}
}
