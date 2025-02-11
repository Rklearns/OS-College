#include <stdio.h>

#define SIZE 5

void avg_waiting_time();
void avg_turn_around();
void completion();
void turn_around();
void waiting_time();

float avg_t = 0;
float avg_w = 0;
int wait[SIZE];
int turn[SIZE];
int arrival[] = {0, 1, 2, 3, 4};
int burst[] = {4, 3, 1, 2, 5};
int complete[SIZE];

int main() {
    completion();
    turn_around();
    waiting_time();
    avg_waiting_time();
    avg_turn_around();
    printf("average waiting time: %.2f\n", avg_w);
    printf("average turnaround time: %.2f\n", avg_t);
    return 0;
}

void completion() {
    int i;
    complete[0] = arrival[0] + burst[0];
    for (i = 1; i < SIZE; i++) {
     complete[i] = complete[i-1]+burst[i];   
    }
}

void turn_around() {
    int i;
    for (i = 0; i < SIZE; i++) {
        turn[i] = complete[i] - arrival[i];
    }
}

void waiting_time() {
    for (int i = 0; i < SIZE; i++) {
        wait[i] = turn[i] - burst[i];
    }
}

void avg_waiting_time() {
    float sum_w = 0;
    for (int i = 0; i < SIZE; i++) {
        sum_w += wait[i];
    }
    avg_w = sum_w / SIZE;
}

void avg_turn_around() {
    float sum_t = 0;
    for (int i = 0; i < SIZE; i++) {
        sum_t += turn[i];
    }
    avg_t = sum_t / SIZE;
}
