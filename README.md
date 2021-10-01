# CollatingListener

This is java based listener that receives one message at a time, store in buffer in sequential order and ship valid batch to processor using fixed interval.

# Assumptions
1. Unique message for each seq number(non-repetitive messages)
2. Messages start from 0.

# Implementation
1. Priority Queue is used as buffer to store incoming messages. It acts as Min heap by sorting based on sequence number.
2. Last index of batch is stored to make sure there is no gap between batches.
3. Valid batch that is continous batch is passed to processor in sorted order by using fixed rate(configurable processing time). 
