CXXFLAGS =	-O2 -g -Wall -fmessage-length=0

OBJS =		Arrays.o

LIBS =

TARGET =	Arrays

$(TARGET):	$(OBJS)
	$(CXX) -o $(TARGET) $(OBJS) $(LIBS)

all:	$(TARGET)

clean:
	rm -f $(OBJS) $(TARGET)
