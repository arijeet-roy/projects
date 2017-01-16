#include <cstdlib>
#include<iostream>
using namespace std;

class node
{
 public:
    int data;
    node* next;
};

class StackAsList
{
public:
StackAsList(int max)
{
    top = NULL;
    maxnum = max;
    count=0;
}

void push(int element)
{
    if(count == maxnum)
            cout<<"stack is full";
    else
    {
        node *newTop = new node;
        if(top == NULL)
        {
            newTop->data = element;
            newTop->next = NULL;
            top = newTop;
            count++;
        }
        else
        {
            newTop->data = element;
            newTop->next = top;
            top = newTop;
            count++;
        }
    }
}

void pop()
{
    if(top == NULL)
            cout<< "nothing to pop";
    else
    {
        node * old = top;
        top = top->next;
        count--;
        delete(old);
    }
}
void print()
{
    node *temp;
    temp = top;
    while(temp!=NULL)
    {
        cout<<temp->data<<",";
        temp=temp->next;
    }
}
private:
    node *top;
    int count; //head
    int maxnum;
    int stackData;
};

int main(int argc, char** argv) {
    StackAsList *sl = new StackAsList(5);
    sl->push(1);
    sl->push(2);
    sl->push(3);

    sl->pop();
    cout<<"new stack\n";
    sl->print();

    return 0;
}
