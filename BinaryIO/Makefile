CXXFLAGS =	-O2 -g -Wall -fmessage-length=0

OBJS =		BinaryIO.o

LIBS =

TARGET =	BinaryIO

$(TARGET):	$(OBJS)
	$(CXX) -o $(TARGET) $(OBJS) $(LIBS)

all:	$(TARGET)

clean:
	rm -f $(OBJS) $(TARGET)
