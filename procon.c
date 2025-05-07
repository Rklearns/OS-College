#include <stdio.h>

// Simulation variables
int mut = 1;
int empty = 5; // total buffer size
int full = 0;
int x = 0;

// Correctly named function: PRODUCER
void producer() {
    mut--; // lock
    x++;   // item number
    full++;
    empty--;
    printf("Producer produced item %d\n", x);
    mut++; // unlock
}

// Correctly named function: CONSUMER
void consumer() {
    mut--; // lock
    printf("Consumer consumed item %d\n", x);
    x--;   // item number decreases
    full--;
    empty++;
    mut++; // unlock
}

int main() {
    int choice = 0;
    while (choice != 3) {
        printf("\nEnter:\n1. Produce\n2. Consume\n3. Exit\n");
        scanf("%d", &choice); // FIXED: read user input

        switch (choice) {
            case 1:
                if (mut == 1 && empty > 0) {
                    producer();
                } else {
                    printf("Cannot produce: Buffer is full or locked.\n");
                }
                break;

            case 2:
                if (mut == 1 && full > 0) {
                    consumer(); // FIXED: calling correct function
                } else {
                    printf("Cannot consume: Buffer is empty or locked.\n");
                }
                break;

            case 3:
                printf("Exiting program...\n");
                break;

            default:
                printf("Invalid choice, try again.\n");
        }
    }
    return 0;
}
