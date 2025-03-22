#include <iostream>
using namespace std;
int main()
{
    int N,M,K;
    int front[100] = {0};
    int back[100] = {0};
    int result[100];
    int sum = 0;
    cin>>N>>M;
    for(int i=0; i<N; i++)
    {
        cin>>front[i]>>back[i];
        result[i] = front[i];
    }
    while(M--)
    {
        cin>>K;
        for(int i=0; i<N; i++)
        {
            //뒤집기
            if(result[i] <= K)
            {
                if(result[i] == front[i])
                    result[i] = back[i];
                else
                    result[i] = front[i];
            }
        }
    }
    for(int i=0; i<N; i++)
        sum += result[i];
    cout<<sum;
    return 0;
}