# redisinaction-learning
study redis in action.

# Start the redis with Docker.
1. Start the docker terminal
2. Run below command to download the redis image from docker hub.
```
docker pull redis
docker run --name redis-test -d redis
docker run -p 6379:6379  --name myredis   -d redis  redis-server --appendonly yes
docker start myredis
```

# Chapter 2 and 3
1. It is better to debug the code in eclipse, because some backgroud thread may clean the data during the runtime. To debug, you could track the data change in the redis.
2. Check out the code comments to find out more about how to handle big amount of data.
3. In Redis, when we talk about a group of commands as being atomic, we mean that no other client can read or change data while we’re reading or changing that same data.
4. SORT allows us to sort LISTs, SETs, and ZSETs according to data in the LIST/SET/ZSET data stored in STRING keys, or even data stored in HASHes
5. If you like the simplicity of using PUBLISH/SUBSCRIBE, and you’re okay with the chance that you may lose a little data, then feel free to use pub/sub instead of our methods, as         we also do in section 8.5; just remember to configure client-output-buffer-limit pubsub reasonably before starting.
6. **In Redis, every command passed as part of a basic MULTI/EXEC transaction is executed one after another until they’ve completed. After they’ve completed, other clients may execute their commands. Redis waits to execute all of the provided commands between MULTI and EXEC until all of the commands have been received and followed by an EXEC.**

7. We can only expire entire keys, not individual items (this is also why we use ZSETs with timestamps in a few places)

## Commands

## String
Command

Example use and description
* INCR	INCR key-name—Increments the value stored at the key by 1
* DECR	DECR key-name—Decrements the value stored at the key by 1
* INCRBY	INCRBY key-name amount—Increments the value stored at the key by the provided integer value
* DECRBY	DECRBY key-name amount—Decrements the value stored at the key by the provided integer value
* INCRBYFLOAT	INCRBYFLOAT key-name amount—Increments the value stored at the key by the provided float value (available in Redis 2.6 and later)

1. APPEND	APPEND key-name value—Concatenates the provided value to the string already stored at the given key
2. GETRANGE	GETRANGE key-name start end—Fetches the substring, including all characters from the start offset to the end offset, inclusive
3. SETRANGE	SETRANGE key-name offset value—Sets the substring starting at the provided offset to the given value
4. GETBIT	GETBIT key-name offset—Treats the byte string as a bit string, and returns the value of the bit in the string at the provided bit offset
5. SETBIT	SETBIT key-name offset value—Treats the byte string as a bit string, and sets the value of the bit in the string at the provided bit offset
6. BITCOUNT	BITCOUNT key-name [start end]—Counts the number of 1 bits in the string, optionally starting and finishing at the provided byte offsets
7. BITOP	BITOP operation dest-key key-name [key-name ...]—Performs one of the bitwise operations, AND, OR, XOR, or NOT, on the strings provided, storing the result in the destination key


## LISTS

* RPUSH	RPUSH key-name value [value ...]—Pushes the value(s) onto the right end of the list
* LPUSH	LPUSH key-name value [value ...]—Pushes the value(s) onto the left end of the list
* RPOP	RPOP key-name—Removes and returns the rightmost item from the list
* LPOP	LPOP key-name—Removes and returns the leftmost item from the list
* LINDEX	LINDEX key-name offset—Returns the item at the given offset
* LRANGE	LRANGE key-name start end—Returns the items in the list at the offsets from start to end, inclusive
* LTRIM	LTRIM key-name start end—Trims the list to only include items at indices between start and end, inclusive
* BLPOP	BLPOP key-name [key-name ...] timeout—Pops the leftmost item from the first non-empty LIST, or waits the timeout in seconds for an item
* BRPOP	BRPOP key-name [key-name ...] timeout—Pops the rightmost item from the first non-empty LIST, or waits the timeout in seconds for an item
* RPOPLPUSH	RPOPLPUSH source-key dest-key—Pops the rightmost item from the source and LPUSHes the item to the destination, also returning the item to the user
* BRPOPLPUSH	BRPOPLPUSH source-key dest-key timeout—Pops the rightmost item from the source and LPUSHes the item to the destination, also returning the item to the user, and waiting up to the timeout if the source is empty

## SETS

* SADD	SADD key-name item [item ...]—Adds the items to the set and returns the number of items added that weren’t already present
* SREM	SREM key-name item [item ...]—Removes the items and returns the number of items that were removed
* SISMEMBER	SISMEMBER key-name item—Returns whether the item is in the SET
* SCARD	SCARD key-name—Returns the number of items in the SET
* SMEMBERS	SMEMBERS key-name—Returns all of the items in the SET as a Python set
* SRANDMEMBER	SRANDMEMBER key-name [count]—Returns one or more random items from the SET. When count is positive, Redis will return count distinct randomly chosen items, and when count is negative, Redis will return count randomly chosen items that may not be distinct.
* SPOP	SPOP key-name—Removes and returns a random item from the SET
* SMOVE	SMOVE source-key dest-key item—If the item is in the source, removes the item from the source and adds it to the destination, returning if the item was moved
* SDIFF	SDIFF key-name [key-name ...]—Returns the items in the first SET that weren’t in any of the other SETs (mathematical set difference operation)
* SDIFFSTORE	SDIFFSTORE dest-key key-name [key-name ...]—Stores at the dest-key the items in the first SET that weren’t in any of the other SETs (mathematical set difference operation)
* SINTER	SINTER key-name [key-name ...]—Returns the items that are in all of the SETs (mathematical set intersection operation)
* SINTERSTORE	SINTERSTORE dest-key key-name [key-name ...]—Stores at the dest-key the items that are in all of the SETs (mathematical set intersection operation)
* SUNION	SUNION key-name [key-name ...]—Returns the items that are in at least one of the SETs (mathematical set union operation)
* SUNIONSTORE	SUNIONSTORE dest-key key-name [key-name ...]—Stores at the dest-key the items that are in at least one of the SETs (mathematical set union operation)

## HASHES

* HMGET	HMGET key-name key [key ...]—Fetches the values at the fields in the HASH
* HMSET	HMSET key-name key value [key value ...]—Sets the values of the fields in the HASH
* HDEL	HDEL key-name key [key ...]—Deletes the key-value pairs in the HASH, returning the number of pairs that were found and deleted
* HLEN	HLEN key-name—Returns the number of key-value pairs in the HASH
* HEXISTS	HEXISTS key-name key—Returns whether the given key exists in the HASH
* HKEYS	HKEYS key-name—Fetches the keys in the HASH
* HVALS	HVALS key-name—Fetches the values in the HASH
* HGETALL	HGETALL key-name—Fetches all key-value pairs from the HASH
* HINCRBY	HINCRBY key-name key increment—Increments the value stored at the given key by the integer increment
* HINCRBYFLOAT	HINCRBYFLOAT key-name key increment—Increments the value stored at the given key by the float increment


## SORTED SETS

* ZADD	ZADD key-name score member [score member ...]—Adds members with the given scores to the ZSET
* ZREM	ZREM key-name member [member ...]—Removes the members from the ZSET, returning the number of members that were removed
* ZCARD	ZCARD key-name—Returns the number of members in the ZSET
* ZINCRBY	ZINCRBY key-name increment member—Increments the member in the ZSET
* ZCOUNT	ZCOUNT key-name min max—Returns the number of members with scores between the provided minimum and maximum
* ZRANK	ZRANK key-name member—Returns the position of the given member in the ZSET
* ZSCORE	ZSCORE key-name member—Returns the score of the member in the ZSET
* ZRANGE	ZRANGE key-name start stop [WITHSCORES]—Returns the members and optionally the scores for the members with ranks between start and stop
* ZREVRANK	ZREVRANK key-name member—Returns the position of the member in the ZSET, with members ordered in reverse
* ZREVRANGE	ZREVRANGE key-name start stop [WITHSCORES]—Fetches the given members from the ZSET by rank, with members in reverse order
* ZRANGEBYSCORE	ZRANGEBYSCORE key min max [WITHSCORES] [LIMIT offset count]—Fetches the members between min and max
* ZREVRANGEBYSCORE	ZREVRANGEBYSCORE key max min [WITHSCORES] [LIMIT offset count]—Fetches the members in reverse order between min and max
* ZREMRANGEBYRANK	ZREMRANGEBYRANK key-name start stop—Removes the items from the ZSET with ranks between start and stop
* ZREMRANGEBYSCORE	ZREMRANGEBYSCORE key-name min max—Removes the items from the ZSET with scores between min and max
* ZINTERSTORE	ZINTERSTORE dest-key key-count key [key ...] [WEIGHTS weight [weight ...]] [AGGREGATE SUM|MIN|MAX]—Performs a SET-like intersection of the provided ZSETs
* ZUNIONSTORE	ZUNIONSTORE dest-key key-count key [key ...] [WEIGHTS weight [weight ...]] [AGGREGATE SUM|MIN|MAX]—Performs a SET-like union of the provided ZSETs

## Expires

* PERSIST	PERSIST key-name—Removes the expiration from a key
* TTL	TTL key-name—Returns the amount of time remaining before a key will expire
* EXPIRE	EXPIRE key-name seconds—Sets the key to expire in the given number of seconds
* EXPIREAT	EXPIREAT key-name timestamp—Sets the expiration time as the given Unix timestamp
* PTTL	PTTL key-name—Returns the number of milliseconds before the key will expire (available in Redis 2.6 and later)
* PEXPIRE	PEXPIRE key-name milliseconds—Sets the key to expire in the given number of milliseconds (available in Redis 2.6 and later)
* PEXPIREAT	PEXPIREAT key-name timestamp-milliseconds—Sets the expiration time to be the given Unix timestamp specified in milliseconds (available in Redis 2.6 and later)


# Chapter 4 Keeping data safe and ensuring performance

## Persisting data to disk
### Snapshots
Within Redis, there are two different ways of persisting data to disk. One is a method called **snapshotting** that takes the data as it exists at one moment in time and writes it to disk. The other method is called **AOF**, or append-only file, and it works by copying incoming write commands to disk as they happen. 

Any Redis client can initiate a snapshot by calling the **BGSAVE** command. On platforms that support BGSAVE (basically all platforms except for Windows), Redis will fork, and the child process will write the snapshot to disk while the parent process continues to respond to commands.                            

When a process forks, the underlying operating system makes a copy of the process. On Unix and Unix-like systems, the copying                  process is optimized such that, initially, all memory is shared between the child and parent processes. When either the parent                  or child process writes to memory, that memory will stop being shared

A Redis client can also initiate a snapshot by calling the **SAVE** command, which causes Redis to stop responding to any/all commands until the snapshot completes. This command isn’t commonly used, except in situations where we need our data on disk, and either we’re okay waiting for it to complete, or we don’t have enough memory for a BGSAVE.

If Redis is configured with save lines, such as *save 60 10000*, Redis will automatically trigger a BGSAVE operation if 10,000 writes have occurred within 60 seconds since the last successful save has started (using the configuration            option described). When multiple save lines are present, any time one of the rules match, a BGSAVE is triggered.

As a point of personal experience, I’ve run Redis servers that used 50 gigabytes of memory on machines with 68 gigabytes of         memory inside a cloud provider running Xen virtualization. When trying to use BGSAVE with clients writing to Redis, forking would take 15 seconds or more, followed by 15–20 minutes for the snapshot to complete.         But with SAVE, the snapshot would finish in 3–5 minutes. For our use, a daily snapshot at 3 a.m. was sufficient, so we wrote scripts that         would stop clients from trying to access Redis, call SAVE, wait for the SAVE to finish, back up the resulting snapshot, and then signal to the clients that they could continue.

### Append-only file persistence

anyone could recover the entire dataset by replaying the append-only log from the beginning to the end. Redis has functionality that does this as well, and it’s enabled by setting the configuration option appendonly yes

* _always_	Every write command to Redis results in a write to disk. This slows Redis down substantially if used.
* _everysec_	Once per second, explicitly syncs write commands to disk.
* _no_	Lets the operating system control syncing to disk.

Append-only files are flexible, offering a variety of options to ensure that almost every level of paranoia can be addressed.         But there’s a dark side to AOF persistence, and that is **file size**.

**Rewriting/compacting append-only files**

Over         time, a growing AOF could cause your disk to run out of space, but more commonly, upon restart, Redis will be executing every         command in the AOF in order. When handling large AOFs, Redis can take a very long time to start up.                  To solve the growing AOF problem, we can use BGREWRITEAOF, which will rewrite the AOF to be as short as possible by removing redundant commands. BGREWRITEAOF works similarly to the snapshotting BGSAVE: performing a fork and subsequently rewriting the append-only log in the child.

Using AOFs, there are two configuration options that enable automatic BGREWRITEAOF execution: *auto-aof-rewrite-percentage* and *auto-aof-rewrite-min-size*. Using the example values of *auto-aof-rewrite-percentage* 100 and *auto-aof-rewrite-min-size* 64mb, when AOF is enabled, Redis will initiate a BGREWRITEAOF when the AOF is at least 100% larger than it was when Redis last finished rewriting the AOF, and when the AOF is at least         64 megabytes in size.

## Replicating data to other machines
Though a variety of options control behavior of the slave itself, only one option is really necessary to enable slaving: **slaveof**. If we were to set *slaveof host port* in our configuration file, the Redis that’s started with that configuration will use the provided host and port as the master         Redis server it should connect to. If we have an already running system, we can tell a Redis server to stop slaving, or even         to slave to a new or different master. To connect to a new master, we can use the SLAVEOF host port command, or if we want to stop updating data from the master, we can use **SLAVEOF no one**.


| Step | Master operations                                                                                        | Slave operations                                                                                          |   |   |
|------|----------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------|---|---|
| 1    | (waiting for a command)                                                                                  | (Re-)connects to the master; issues the SYNC command                                                      |   |   |
| 2    | Starts BGSAVE operation; keeps a backlog of all write commands sent after BGSAVE                         | Serves old data (if any), or returns errors to commands (depending on configuration)                      |   |   |
| 3    | Finishes BGSAVE; starts sending the snapshot to the slave; continues holding a backlog of write commands | Discards all old data (if any); starts loading the dump as it’s received                                  |   |   |
| 4    | Finishes sending the snapshot to the slave; starts sending the write command backlog to the slave        | Finishes parsing the dump; starts responding to commands normally again                                   |   |   |
| 5    | Finishes sending the backlog; starts live streaming of write commands as they happen                     | Finishes executing backlog of write commands from the master; continues executing commands as they happen |   |   |


* when a slave initially connects to a master, any data that had been in memory will be lost, to be replaced by the data coming from the         master. 
* Redis doesn’t support master-master replication


Below is the code to verify the data is sync between master and slave.
```python
def wait_for_sync(mconn, sconn):
    identifier = str(uuid.uuid4())
    mconn.zadd('sync:wait', {identifier: time.time()})      #A

    while not sconn.info()['master_link_status'] != 'up':   #B
        time.sleep(.001)

    while not sconn.zscore('sync:wait', identifier):        #C
        time.sleep(.001)

    deadline = time.time() + 1.01                           #D
    while time.time() < deadline:                           #D
        if sconn.info()['aof_pending_bio_fsync'] == 0:      #E
            break                                           #E
        time.sleep(.001)

    mconn.zrem('sync:wait', identifier)                     #F
    mconn.zremrangebyscore('sync:wait', 0, time.time()-900) #F
# <end id="wait-for-sync"/>
#A Add the token to the master
#B Wait for the slave to sync (if necessary)
#C Wait for the slave to receive the data change
#D Wait up to 1 second
#E Check to see if the data is known to be on disk
#F Clean up our status and clean out older entries that may have been left there
#END
```
**By combining replication and append-only files, we can configure Redis to be resilient against system failures.**


## Dealing with system failures
## Redis transactions
## Non-transactional pipelines
## Diagnosing performance issues