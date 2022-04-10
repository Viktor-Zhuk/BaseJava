package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void deleteResume(int index) {
        int lengthShift = size - index - 1;
        if (lengthShift > 0) {
            System.arraycopy(storage, index +1, storage, index, lengthShift);
        }
    }

    @Override
    public void saveResume(Resume resume, int index) {
        int indexNewResume = - index - 1;
        System.arraycopy(storage, index, storage, index + 1, size - indexNewResume);
        storage[indexNewResume] = resume;
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
