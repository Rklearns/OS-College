#include<stdio.h>
#include<string.h>
#include<stdlib.h>

//lets start with the code for fcfs
#define n 5

int at[n];
int bt[n];
int tat[n];
int wt[n];
int ct[n];
int main()
{
    printf("input arrival time and burst time for process\n");
    for(int i=0;i<n;i++)
    {
        scanf("%d %d",&at[i], &bt[i]);     
    }  

//we have to ensure that arrival time is sorted in ascending order 
for(int i=0;i<n-1;i++)
{
    for(int j=0;j<n-i-1;j++)
    {
        if(at[j]>at[j+1])
        {
            int temp=at[j];
            at[j]=at[j+1];
            at[j+1]=temp;

            //ye arrival time ko sort kardeta like processed ko in order how they come first
            temp=bt[j];
            bt[j]=bt[j+1];
            bt[j+1]=temp;
        
        }
    }
}

//now we will find the completion time 
ct[0]=at[0]+bt[0];
for(int i=1;i<n;i++)
{
    if(at[i] > ct[i - 1])
    ct[i] = at[i] + bt[i];
else
    ct[i] = ct[i - 1] + bt[i];

}

//now we will find the waiting and turnaround time
int turn=0;
int wait=0;
for(int i=0;i<n;i++)
{
    tat[i]=ct[i]-at[i];
    wt[i]=tat[i]-bt[i];
    turn+=tat[i];
    wait+=wt[i];
}
printf("\nProcess\tAT\tBT\tCT\tTAT\tWT\n");
    for(int i = 0; i < n; i++) {
        printf("P%d\t%d\t%d\t%d\t%d\t%d\n", i + 1, at[i], bt[i], ct[i], tat[i], wt[i]);
    }

    printf("\nAverage Turnaround Time = %.2f", (float)turn / n);
    printf("\nAverage Waiting Time = %.2f\n", (float)wait / n);

return 0;
}
