<template>
  <q-tab-panels :model-value="model.activeGroupKey" class="full-width full-height bg-transparent" vertical animated>
    <q-tab-panel v-for="group in model.groups" :key="group.key" :name="group.key" class="full-width full-height q-pt-sm">
      <q-scroll-area dark class="full-width full-height">
        <div class="row q-pl-xs non-selectable content-start items-start start-menu-item-list-control">
          <div v-for="subgroup in group.subgroups" :key="subgroup.key" class="row col-all start-menu-item-subgroup-control">
            <div class="col-all q-pb-sm sub-group-header-control">{{ subgroup.label }}</div>
            <div class="row col-all q-gutter-md">
              <div v-for="item in subgroup.items" :key="item.key" class="column items-center text-center start-menu-item-control">
                <q-btn flat square stack class="start-menu-item-icon" @click="model.goto(item.to)">
                  <q-icon :name="item.icon"/>
                </q-btn>
                <div class="q-mt-xs start-menu-item-label">{{ item.label }}</div>
              </div>
            </div>
          </div>
        </div>
      </q-scroll-area>
    </q-tab-panel>
  </q-tab-panels>
</template>

<script setup lang="ts">
import {UnwrapRef} from 'vue';
import {StartMenu} from 'layouts/main/StartMenu';

defineProps<{
  model: UnwrapRef<StartMenu>
}>();
</script>
