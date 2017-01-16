CXXFLAGS =	-O2 -g -Wall -fmessage-length=0

OBJS =		CircularArray.o

LIBS =

TARGET =	CircularArray

$(TARGET):	$(OBJS)
	$(CXX) -o $(TARGET) $(OBJS) $(LIBS)

all:	$(TARGET)

clean:
	rm -f $(OBJS) $(TARGET)
