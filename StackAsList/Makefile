CXXFLAGS =	-O2 -g -Wall -fmessage-length=0

OBJS =		StackAsList.o

LIBS =

TARGET =	StackAsList

$(TARGET):	$(OBJS)
	$(CXX) -o $(TARGET) $(OBJS) $(LIBS)

all:	$(TARGET)

clean:
	rm -f $(OBJS) $(TARGET)
