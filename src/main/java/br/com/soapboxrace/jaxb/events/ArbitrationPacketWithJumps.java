package br.com.soapboxrace.jaxb.events;

public interface ArbitrationPacketWithJumps
{
    long getLongestJumpDurationInMilliseconds();

    long getSumOfJumpsDurationInMilliseconds();
}
