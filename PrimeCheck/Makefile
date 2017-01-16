CXXFLAGS =	-O2 -g -Wall -fmessage-length=0

OBJS =		PrimeCheck.o

LIBS =

TARGET =	PrimeCheck

$(TARGET):	$(OBJS)
	$(CXX) -o $(TARGET) $(OBJS) $(LIBS)

all:	$(TARGET)

clean:
	rm -f $(OBJS) $(TARGET)
