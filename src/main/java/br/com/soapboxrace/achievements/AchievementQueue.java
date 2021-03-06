package br.com.soapboxrace.achievements;

import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Holds persona achievement queues.
 * 
 * @author leorblx
 */
public class AchievementQueue
{
    private static final ConcurrentMap<Long, PersonaAchievementQueue> queueMap = new ConcurrentHashMap<>();

    public static ConcurrentMap<Long, PersonaAchievementQueue> getQueueMap()
    {
        return queueMap;
    }

    /**
     * Gets an achievement queue by persona ID.
     * 
     * @param personaId The persona ID to get the queue for.
     * @return The achievement queue.
     */
    public static PersonaAchievementQueue get(Long personaId)
    {
        return queueMap.computeIfAbsent(personaId, PersonaAchievementQueue::new);
    }

    /**
     * A persona-specific achievement queue.
     */
    public static class PersonaAchievementQueue
    {
        private boolean free = true;
        private boolean dirty = false;
        private Queue<AchievementUpdateInfo> updateInfoQueue;
        private Long personaId;

        public PersonaAchievementQueue(Long personaId)
        {
            this.personaId = personaId;
            this.updateInfoQueue = new LinkedBlockingQueue<>();
        }

        /**
         * Add an achievement update object to the queue.
         *
         * @param updateInfo The update to add.
         * @throws IllegalStateException if the queue is no longer free
         */
        public void add(AchievementUpdateInfo updateInfo)
        {
            if (!this.free) {
                throw new IllegalStateException("Queue is no longer free for persona ID " + personaId);
            }
            
            updateInfoQueue.offer(updateInfo);
            this.dirty = true;
        }

        /**
         * Locks the queue. No new achievement updates can be added.
         */
        public void lock()
        {
            this.free = false;
        }

        /**
         * Unlocks the queue. New achievement updates can once again be added.
         */
        public void unlock()
        {
            this.free = true;
        }

        /**
         * Gets the queue of update info objects.
         *
         * @return The queue of update info objects.
         */
        public Queue<AchievementUpdateInfo> getUpdateInfoQueue()
        {
            return updateInfoQueue;
        }

        /**
         * Clear the queue immediately.
         */
        public void clear()
        {
            this.dirty = !updateInfoQueue.isEmpty();
            updateInfoQueue.clear();
        }

        /**
         * Return if the queue is free or not.
         * 
         * @return If the queue is free or not.
         */
        public boolean isFree()
        {
            return free;
        }

        /**
         * Return if the queue is dirty or not.
         * 
         * @return If the queue is dirty or not.
         */
        public boolean isDirty()
        {
            return dirty;
        }
    }
}
